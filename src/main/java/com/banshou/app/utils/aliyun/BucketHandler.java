package com.banshou.app.utils.aliyun;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.AccessControlList;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.banshou.app.utils.common.Config;

/**
 * Aliyun OSS Bucket操作类
 * 
 */
public class BucketHandler {

	private static final String ACCESS_ID = Config.ALIKEYID;
	private static final String ACCESS_KEY = Config.ALIKEYSCR;
	private static final String OSS_ENDPOINT = Config.ALIURL;
	private static OSSClient client = null;

	public BucketHandler() {
		client = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
	}

	/**
	 * bucketName为当前bucket, objectName为要创建的文件夹名称,在满足Object命名规则的情况下以"/"结尾, eg:
	 * example/
	 * 
	 * @param oriBucketName
	 * @param objectName
	 */
	public void createFolder(String bucketName, String objectName) {
		ObjectMetadata objectMeta = new ObjectMetadata();
		byte[] buffer = new byte[0];
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		objectMeta.setContentLength(0);
		try {
			client.putObject(bucketName, objectName, in, objectMeta);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String uploadFile(String bucketName, String key, File file)
			throws OSSException, ClientException, FileNotFoundException {
		//File file = new File(filename);

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.length());
		objectMeta.setContentType("image/jpeg");

		InputStream input = new FileInputStream(file);
		PutObjectResult result = client.putObject(bucketName, key, input,
				objectMeta);
		return result.getETag();
	}

	public void downloadFile(String bucketName, String key, String filename) {
		client.getObject(new GetObjectRequest(bucketName, key), new File(
				filename));
	}

	/**
	 * 删除bucket及下面的所有文件
	 * 
	 * @param bucketName
	 */
	public void deleteBucket(String bucketName) {
		ObjectListing ObjectListing = client.listObjects(bucketName);
		List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
		for (int i = 0; i < listDeletes.size(); i++) {
			String objectName = listDeletes.get(i).getKey();
			client.deleteObject(bucketName, objectName);
		}
		client.deleteBucket(bucketName);
	}

	public void deleteFile(String bucketName, String objectName) {
		client.deleteObject(bucketName, objectName);
	}

	/**
	 * 创建并且设置bucket属性,共三种:私有,可读,可读写
	 * 
	 * @param bucketName
	 * @throws OSSException
	 * @throws ClientException
	 */
	public void setBucketPublicReadable(String bucketName) throws OSSException,
			ClientException {
		client.createBucket(bucketName);
		client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
	}

	/**
	 * 传入bucketName,可以拿到该bucket的概要信息
	 * 
	 * @param bucketName
	 * @return
	 */
	public String getBucketDetails(String bucketName) {
		client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
		AccessControlList accessControlList = client.getBucketAcl(bucketName);
		return accessControlList.toString();
	}

	public String getBucketLocation(String bucketName) {
		try {
			String location = client.getBucketLocation(bucketName);
			return location;
		} catch (OSSException e) {
			e.printStackTrace();
			return "";
		}
	}

}
