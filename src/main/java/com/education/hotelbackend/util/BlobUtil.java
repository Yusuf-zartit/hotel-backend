package com.education.hotelbackend.util;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class BlobUtil {

    public static String blobToString(Blob blob) {
        if (blob == null) {
            return null;
        }

        try {
            byte[] bytes = blob.getBytes(1, (int) blob.length());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (SQLException e) {
            throw new RuntimeException("Error converting Blob to string", e);
        }
    }
}
