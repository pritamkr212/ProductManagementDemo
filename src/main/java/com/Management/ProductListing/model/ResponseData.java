package com.Management.ProductListing.model;

public class ResponseData {
    private Product product;
    private String fileName;
    private String downloadURL;
    private String fileType;
    private long fileSize;

    public ResponseData(Product product, String fileName, String downloadURL, String fileType, long fileSize) {
        this.product=product;
        this.fileName = fileName;
        this.downloadURL = downloadURL;
        this.fileType = fileType;
        this.fileSize = fileSize;
    }

    public ResponseData() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "ResponseDate{" +
                "product=" + product +
                ", fileName='" + fileName + '\'' +
                ", downloadURL='" + downloadURL + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
