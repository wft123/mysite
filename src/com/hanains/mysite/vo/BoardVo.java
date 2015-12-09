package com.hanains.mysite.vo;

public class BoardVo {
		private long no;
		private String title;
		private String content;
		private long member_no;
		private long view_cnt;
		private String reg_date;
		private String member_name;
		
		public BoardVo(){}

		public long getNo() {
			return no;
		}

		public void setNo(long no) {
			this.no = no;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public long getMember_no() {
			return member_no;
		}

		public void setMember_no(long member_no) {
			this.member_no = member_no;
		}

		public long getView_cnt() {
			return view_cnt;
		}

		public void setView_cnt(long view_cnt) {
			this.view_cnt = view_cnt;
		}

		public String getReg_date() {
			return reg_date;
		}

		public void setReg_date(String reg_date) {
			this.reg_date = reg_date;
		}

		public String getMember_name() {
			return member_name;
		}

		public void setMember_name(String member_name) {
			this.member_name = member_name;
		}

		@Override
		public String toString() {
			return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", member_no=" + member_no
					+ ", view_cnt=" + view_cnt + ", reg_date=" + reg_date + ", member_name=" + member_name + "]";
		}
}
