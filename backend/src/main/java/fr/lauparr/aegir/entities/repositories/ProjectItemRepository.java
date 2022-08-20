package fr.lauparr.aegir.entities.repositories;

import fr.lauparr.aegir.entities.ProjectItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectItemRepository extends PagingAndSortingRepository<ProjectItem, Long>, JpaSpecificationExecutor<ProjectItem> {

  @Query("select p from ProjectItem p where p.project.id = :projectId and p.parent is null")
  List<ProjectItem> getByProjectId(@Param("projectId") Long projectId);

  @Override
  @Modifying
  @Transactional
  @Query("delete from ProjectItem p where p.id = :id")
  void deleteById(@Param("id") Long id);
}
