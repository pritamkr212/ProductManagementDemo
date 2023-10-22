package com.Management.ProductListing.controller;

import com.Management.ProductListing.model.*;
import com.Management.ProductListing.model.ResponseData;
import com.Management.ProductListing.repository.ProductRepository;
import com.Management.ProductListing.service.AttachmentService;
import com.Management.ProductListing.service.EventLogService;
import com.Management.ProductListing.service.ProductService;
import com.Management.ProductListing.utils.UuidConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
@RequestMapping("api/v1/")
public class ProductController {
    private static final Logger logger= LoggerFactory.getLogger(ProductController.class);
    @Autowired private ProductService productService;
    @Autowired private ProductRepository productRepository;
    @Autowired private UuidConversion uuidConversion;
    @Autowired private EventLogService eventLogService;
    @Autowired private AttachmentService attachmentService;


    @PostMapping("/saveProduct")
    public ResponseData saveProduct(@ModelAttribute ProductWithAttachment productWithAttachment) throws Exception {
        // Access productWithImage.getProduct() for JSON data
        Product product=productWithAttachment.getProduct();
        // Access productWithImage.getFile() for the uploaded file
        MultipartFile multipartFile=productWithAttachment.getFile();

        // Your processing logic here

        logger.debug("Saving New Product {}",product.getProductId());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("createdTime", String.valueOf(Instant.now().getEpochSecond()));
        String id= uuidConversion.StringToUUID(product.getProductId().toLowerCase());
        EventLog eventLog=new EventLog(id,Instant.now().getEpochSecond(),null,null,"/saveProduct",-1,-1);
        logger.debug("New Event {}" ,eventLog);
        eventLogService.saveEvent(eventLog);

        try{
            if(productRepository.findById(id).orElse(null)==null){
                product.setId(id);
                responseHeaders.add("X-Response-ID", product.getId());
                ResponseEntity<?>res= new ResponseEntity<>(productService.saveProduct(product,id),responseHeaders, HttpStatus.CREATED);
                logger.debug("Saved New Product {}" ,product.getProductId());

                Attachment attachment=null;
                String downloadURL="";
                attachment = attachmentService.saveAttachment(id,multipartFile);
                downloadURL= ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download")
                        .path(attachment.getId())
                        .toUriString();
                return new ResponseData(product,
                        attachment.getFileName(),
                        downloadURL,
                        multipartFile.getContentType(),
                        multipartFile.getSize());
            }else{
                logger.debug("Duplicate Product Found : Updating to Save Product {}" ,product.getProductId());
                ResponseEntity<?>res= new ResponseEntity<>(productService.saveProduct(product,id), HttpStatus.OK);
                logger.debug("Product SuccessFully updated {}",id);
//                return res;
            }
        }
        catch (Exception e){
            logger.debug("Error occurred {} ",e);
            throw new Exception(e);

        }

//        return ResponseEntity.ok("Product and image saved successfully.");
        return null;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource>downloadData(@PathVariable String id)throws Exception{
        Attachment attachment=null;
        attachment=attachmentService.getAttachment(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""+attachment.getFileName()+"\"")
                .body(new ByteArrayResource(attachment.getData()));
    }
    @PostMapping("/saveProduct")
    public ResponseEntity<?>saveProduct(@RequestBody Product product) throws Exception {
        logger.debug("Saving New Product {}",product.getProductId());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("createdTime", String.valueOf(Instant.now().getEpochSecond()));
        String id= uuidConversion.StringToUUID(product.getProductId().toLowerCase());
        EventLog eventLog=new EventLog(id,Instant.now().getEpochSecond(),null,null,"/saveProduct",-1,-1);
        logger.debug("New Event {}" ,eventLog);
        eventLogService.saveEvent(eventLog);

        try{
            if(productRepository.findById(id).orElse(null)==null){
                product.setId(id);
                responseHeaders.add("X-Response-ID", product.getId());
                ResponseEntity<?>res= new ResponseEntity<>(productService.saveProduct(product,id),responseHeaders, HttpStatus.CREATED);
                logger.debug("Saved New Product {}" ,product.getProductId());
                return res;
            }else{
                logger.debug("Duplicate Product Found : Updating to Save Product {}" ,product.getProductId());
                ResponseEntity<?>res= new ResponseEntity<>(productService.saveProduct(product,id), HttpStatus.OK);
                logger.debug("Product SuccessFully updated {}",id);
                return res;
            }
        }
        catch (Exception e){
            logger.debug("Error occurred {} ",e);
            throw new Exception(e);

        }

    }
    @GetMapping("/products")
    public ResponseEntity<?>getAllProduct(){
        logger.debug("Getting All Product");
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?>getProductById(@PathVariable String id){
        logger.info("Getting Specific Product {}",id);
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }
    @DeleteMapping("/deleteProduct/{id}")
    public  ResponseEntity<?>deleteProductById(@PathVariable String id){
        logger.debug("Deleting the Product {}",id);
        ResponseEntity<?>res= new ResponseEntity<>(productService.deleteProductById(id),HttpStatus.OK);
        logger.debug("Product SuccessFully deleted {}",id);
        return res;
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<?>updateProduct(@RequestBody Product product,@PathVariable String productId){
        logger.debug("Updating the Product {}",productId);
        ResponseEntity<?>res= new ResponseEntity<>(productService.saveProduct(product,productId), HttpStatus.CREATED);
        logger.debug("Product SuccessFully updated {}",productId);
        return res;

    }

}
