package br.com.devdojo.awesome.error;

public class ErrorDetails {

	private String title;
	private int status;
	private String detail;
	private long timeStamp;
	private String developerMessage;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private long timeStamp;
		private String developerMessage;

		private Builder() {

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

		public ErrorDetails build() {
			ErrorDetails erroDetails = new ErrorDetails();
			erroDetails.setDeveloperMessage(developerMessage);
			erroDetails.setTitle(title);
			erroDetails.setDetail(detail);
			erroDetails.setTimeStamp(timeStamp);
			erroDetails.setStatus(status);

			return erroDetails;
		}
	}

}
