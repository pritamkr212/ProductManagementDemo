package com.Management.ProductListing.service;

import com.Management.ProductListing.model.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    public Attachment saveAttachment(String id,MultipartFile multipartFile) throws Exception;
    public Attachment getAttachment(String fileId) throws Exception;
}
