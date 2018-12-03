package br.com.devdojo.awesome.error;

import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

public class ResourceNotFoundDetails {
	
	private String title;
	private int status;
	private String detail;
	private long timeStamp;
	private String developerMessage;

	private ResourceNotFoundDetails() {
		
	}

	public String getTitle() {
		return title;
	}

	public int getStatus() {
		return status;
	}

	public String getDetail() {
		return detail;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}
	
	public static final class Builder
	{
		private String title;
		private int status;
		private String detail;
		private long timeStamp;
		private String developerMessage;
		
		private Builder(){
			
		}
		
		public static Builder newBuilder() {
			return new Builder();
		}
		
		public Builder title(String title) {
			this.title = title;
			return this;
		}
		
		public Builder status(int status) {
			this.status = status;
			return this;
		}
		
		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}
		
		public Builder timeStamp(long timeStamp) {
			this.timeStamp = timeStamp;
			return this;
		}
		
		public Builder developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public ResourceNotFoundDetails build() {
			ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
			resourceNotFoundDetails.setDeveloperMessage(developerMessage);
			resourceNotFoundDetails.setTitle(title);
			resourceNotFoundDetails.setDetail(detail);
			resourceNotFoundDetails.setTimeStamp(timeStamp);
			resourceNotFoundDetails.setStatus(status);
			return resourceNotFoundDetails;
		}
	}
	
}
