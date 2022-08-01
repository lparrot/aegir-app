package fr.lauparr.aegir.features.project_item;

import fr.lauparr.aegir.enums.EnumProjectItemType;
import org.springframework.beans.factory.annotation.Value;

public interface ProjectItemInfo {
  Long getId();

  String getName();

  EnumProjectItemType getType();

  @Value("#{target.parent?.id}")
  Long getParentId();

  @Value("#{target.getItemNameHierarchy()}")
  String[] getItemHierarchy();
}