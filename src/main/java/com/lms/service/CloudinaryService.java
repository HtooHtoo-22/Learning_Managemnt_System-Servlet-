package com.lms.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    /**
     * Uploads a file to Cloudinary and returns the URL of the uploaded file.
     *
     * @param file the file to be uploaded
     * @return the URL of the uploaded file
     * @throws IOException if the file could not be uploaded
     */
    public String uploadFile(MultipartFile file) throws IOException {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), 
                    ObjectUtils.asMap("resource_type", "auto")); // "auto" allows images, PDFs, videos, etc.
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new IOException("Error uploading file to Cloudinary", e);
        }
    }

    /**
     * Deletes a file from Cloudinary using the public_id.
     *
     * @param publicId the public_id of the file to be deleted
     * @return a confirmation message from Cloudinary
     * @throws IOException if the file could not be deleted
     */
    public String deleteFile(String publicId) throws IOException {
        try {
            Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return result.get("result").toString();
        } catch (IOException e) {
            throw new IOException("Error deleting file from Cloudinary", e);
        }
    }
}
