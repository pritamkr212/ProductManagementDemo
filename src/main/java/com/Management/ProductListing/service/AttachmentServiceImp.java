package com.Management.ProductListing.service;

import com.Management.ProductListing.model.Attachment;
import com.Management.ProductListing.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
@Service
public class AttachmentServiceImp implements AttachmentService{
    @Autowired AttachmentRepository attachmentRepository;
    @Override
    public Attachment saveAttachment(String id, MultipartFile multipartFile) throws Exception {
        String fileName= StringUtils.cleanPath(multipartFile.getOriginalFilename());
        try {
            if(fileName.contains("..")){
                throw new Exception("FileName contains invaild path sequence "+fileName);
            }
            Attachment attachment
                    = new Attachment(id,
                    fileName,
                    multipartFile.getContentType(),
                    multipartFile.getBytes());
            return attachmentRepository.save(attachment);

        }catch (Exception e){
            throw new Exception("Could not save File: "+fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository.findById(fileId).orElseThrow(()->new Exception("File not Found with Id: "+fileId));
    }
}
