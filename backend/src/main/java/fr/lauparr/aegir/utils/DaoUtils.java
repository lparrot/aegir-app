package fr.lauparr.aegir.utils;

import fr.lauparr.aegir.config.AutowireHelper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DaoUtils {

  private static final String SPLIT_CHAR = "\\.";

  public static <T> T convertToDto(final Object data, final Class<T> entityClass) {
    if (data == null) {
      return null;
    }
    return AutowireHelper.getBean(ProjectionFactory.class).createProjection(entityClass, data);
  }

  public static <T> List<T> convertListDto(final List<?> liste, final Class<T> entityClass) {
    if (liste == null) {
      return new ArrayList<>();
    }
    return liste.stream().map(x -> DaoUtils.convertToDto(x, entityClass)).collect(Collectors.toList());
  }

  public static <T> Page<T> convertPageDto(final Page<?> page, final Class<T> entityClass) {
    if (page == null) {
      return Page.empty();
    }
    return page.map(x -> DaoUtils.convertToDto(x, entityClass));
  }

  public static <T> T findRandom(final PagingAndSortingRepository<T, ?> repository) {
    final long count = repository.count();
    final int idx = new SecureRandom().nextInt((int) count);
    final List<T> result = repository.findAll(PageRequest.of(idx, 1)).getContent();
    if (!result.isEmpty()) {
      return result.get(0);
    }
    return null;
  }

  /**
   * Récupère le Path à partir d'un Root
   */
  public static <T> Path<T> getPathFromRoot(final Root<T> root, final String field) {
    final String principal;
    String[] fields = null;
    if (field != null) {
      final String[] checks = field.split(DaoUtils.SPLIT_CHAR);
      principal = checks[0];
      if (checks.length > 1) {
        fields = Arrays.copyOfRange(checks, 1, checks.length);
      }
    } else {
      principal = null;
    }
    final Path<T> p;
    if (DaoUtils.isSubPath(fields)) {
      p = DaoUtils.crossPathToPath(root.join(principal, JoinType.LEFT), fields);
    } else {
      p = root.get(principal);
    }
    return p;
  }

  /**
   * Vérifie qu'il ne s'agit pas du dernier Path "extractible"
   */
  private static boolean isSubPath(final String[] fields) {
    return fields != null && fields.length > 0;
  }

  /**
   * Récupère un Path à partir du Path et de la liste de champs filtrés
   */
  private static <T> Path<T> crossPathToPath(final Path<T> path, final String[] fields) {
    Path<T> lastPath = path;
    if (fields != null) {
      for (final String key : fields) {
        lastPath = lastPath.get(key);
      }
    }
    return lastPath;
  }


}