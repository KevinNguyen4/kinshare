// filepath: c:\Users\KDN20\OneDrive\Documents\425\kinshare\src\main\java\com\guardianware\kinshare\repository\TrainingProfileRepository.java
package com.guardianware.kinshare.repository;

import com.guardianware.kinshare.entity.TrainingProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingProfileRepository extends JpaRepository<TrainingProfile, Long> {
}