package fr.lauparr.aegir.features.user;

import fr.lauparr.aegir.features.project.ProjectInfo;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface UserInfo {
  Long getId();

  String getUsername();

  @Value("#{target.profile?.id}")
  Long getProfileId();

  @Value("#{target.profile?.label}")
  String getProfileLabel();

  @Value("#{target.projects}")
  List<ProjectInfo> getProjects();

  @Value("#{target.userData?.id}")
  Long getUserDataId();

  @Value("#{target.userData?.email}")
  String getUserDataEmail();

  @Value("#{target.userData?.firstname}")
  String getUserDataFirstname();

  @Value("#{target.userData?.lastname}")
  String getUserDataLastname();

  @Value("#{target.userData?.address}")
  String getUserDataAddress();

  @Value("#{target.userData?.city}")
  String getUserDataCity();

  @Value("#{target.userData?.postalCode}")
  String getUserDataPostalCode();

  @Value("#{target.userData?.about}")
  String getUserDataAbout();

  @Value("#{target.userData?.getFullname()}")
  String getUserDataFullname();
}
