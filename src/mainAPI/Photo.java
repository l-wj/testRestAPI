package mainAPI;

public class Photo {
	private String albumId;
	private String id;
	private String title;
	private String url;
	private String thumbnailUrl;

		public String getualbumId() {
			return albumId;
		}

		public String getid() {
			return id;
		}

		public String gettitle() {
			return title;
		}

		public String geturl() {
			return url;
		}
		public String getthumbnailUrl() {
			return thumbnailUrl;
		}
		public void setuserId(String albumId) {
			this.albumId = albumId;
		}

		public void setid(String id) {
			this.id= id;
		}

		public void settitle(String  title) {
			this.title= title;
		}

		public void seturl(String url) {
			this.url= url;
		}
		public void setthumbnailUrl(String thumbnailUrl) {
			this.thumbnailUrl= thumbnailUrl;
		}
}
