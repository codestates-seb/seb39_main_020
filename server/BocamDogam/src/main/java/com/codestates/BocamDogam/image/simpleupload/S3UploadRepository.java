package com.codestates.BocamDogam.image.simpleupload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S3UploadRepository extends JpaRepository<S3UploadEntity, Long> {

}
