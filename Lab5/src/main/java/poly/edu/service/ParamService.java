package poly.edu.service;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;

    /** Đọc chuỗi */
    public String getString(String name, String defaultValue) {
        String v = request.getParameter(name);
        return (v == null || v.isBlank()) ? defaultValue : v.trim();
    }

    /** Đọc int */
    public int getInt(String name, int defaultValue) {
        String v = request.getParameter(name);
        if (v == null || v.isBlank()) return defaultValue;
        try {
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /** Đọc double */
    public double getDouble(String name, double defaultValue) {
        String v = request.getParameter(name);
        if (v == null || v.isBlank()) return defaultValue;
        try {
            return Double.parseDouble(v.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /** Đọc boolean */
    public boolean getBoolean(String name, boolean defaultValue) {
        String v = request.getParameter(name);
        if (v == null) return defaultValue;
        // chấp nhận true/false, on/off, 1/0
        v = v.trim().toLowerCase();
        if ("true".equals(v) || "on".equals(v) || "1".equals(v)) return true;
        if ("false".equals(v) || "off".equals(v) || "0".equals(v)) return false;
        return defaultValue;
    }

    /** Đọc Date với pattern, ném lỗi nếu sai định dạng */
    public Date getDate(String name, String pattern) {
        String v = request.getParameter(name);
        if (v == null || v.isBlank()) return null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            return sdf.parse(v.trim());
        } catch (ParseException e) {
            throw new RuntimeException("Sai định dạng ngày: " + pattern, e);
        }
    }

    /** Lưu file upload vào /static/<path> (path tính từ webroot resources/static) */
    public File save(MultipartFile file, String path) {
        try {
            if (file == null || file.isEmpty()) return null;
            // Thư mục đích trong target/classes/static/... khi chạy dev
            String root = request.getServletContext().getRealPath("/");
            // Khi chạy Spring Boot, getRealPath có thể null => fallback vào project dir
            if (root == null) root = new File(".").getAbsolutePath();
            File dir = new File(root, path);
            if (!dir.exists() && !dir.mkdirs()) {
                throw new RuntimeException("Không tạo được thư mục: " + dir.getAbsolutePath());
            }
            File saved = new File(dir, file.getOriginalFilename());
            file.transferTo(saved);
            return saved;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi lưu file upload", e);
        }
    }
}

