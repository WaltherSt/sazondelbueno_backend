package sazondelbueno.web.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sazondelbueno.web.Utils.ApiResponse;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.bucket.url}")
    private String bucketUrl;

    public ApiResponse uploadFile(String key, MultipartFile file) throws IOException {
        String type = Objects.requireNonNull(file.getContentType()).split("/")[1];
        String fileName = UUID.randomUUID().toString() +"."+ type;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, file.getInputStream(), null);
        amazonS3.putObject(putObjectRequest);
        return new ApiResponse("File uploaded successfully!", bucketUrl + "/" + fileName);
    }
}
