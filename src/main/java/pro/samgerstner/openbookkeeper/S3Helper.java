package pro.samgerstner.openbookkeeper;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.util.Random;

public class S3Helper
{
   private String accessKey;
   private String secretKey;
   private String region;
   private String bucketName;
   private AwsCredentials credentials;
   private S3Client client;

   public S3Helper(String accessKey, String secretKey, String region, String bucketName)
   {
      this.accessKey = accessKey;
      this.secretKey = secretKey;
      this.region = region;
      this.bucketName = bucketName;
      credentials = AwsBasicCredentials.create(this.accessKey, this.secretKey);
      AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(this.credentials);
      Region awsRegion = Region.of(this.region);
      client = S3Client.builder().credentialsProvider(credentialsProvider).region(awsRegion).build();
   }

   public PutObjectResponse createJournalEntryFolder(int journalEntryID)
   {
      String folderName = "journal_attachments/" + journalEntryID + "/";
      PutObjectRequest req = PutObjectRequest.builder().bucket(this.bucketName).key(folderName).build();
      return this.client.putObject(req, RequestBody.empty());
   }

   public DeleteObjectResponse deleteJournalEntryFolder(int journalEntryID)
   {
      String folderName = "journal_attachments/" + journalEntryID + "/";
      DeleteObjectRequest req = DeleteObjectRequest.builder().bucket(this.bucketName).key(folderName).build();
      return this.client.deleteObject(req);
   }

   public PutObjectResponse uploadJournalEntryAttachment(int journalEntryID, String fileName, byte[] data)
   {
      String completeFileName = "journal_attachments/" + journalEntryID + "/" + fileName;
      PutObjectRequest req = PutObjectRequest.builder().bucket(this.bucketName).key(completeFileName).build();
      return this.client.putObject(req, RequestBody.fromBytes(data));
   }

   public byte[] downloadJournalEntryAttachment(int journalEntryID, String fileName)
   {
      String completeFileName = "journal_attachments/" + journalEntryID + "/" + fileName;
      GetObjectRequest req = GetObjectRequest.builder().bucket(this.bucketName).key(completeFileName).build();
      ResponseBytes<GetObjectResponse> objectBytes = this.client.getObjectAsBytes(req);
      return objectBytes.asByteArray();
   }

   public DeleteObjectResponse deleteJournalEntryAttachment(int journalEntryID, String fileName)
   {
      String completeFileName = "journal_attachments/" + journalEntryID + "/" + fileName;
      DeleteObjectRequest req = DeleteObjectRequest.builder().bucket(this.bucketName).key(completeFileName).build();
      return this.client.deleteObject(req);
   }

   public String generateDownloadNonce()
   {
      String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
      StringBuilder builder = new StringBuilder();
      Random random = new Random();

      while(builder.length() < 32)
      {
         int index = (int) (random.nextFloat() * chars.length());
         builder.append(chars.charAt(index));
      }

      return builder.toString();
   }
}