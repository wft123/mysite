package com.hanains.mysite.http.action.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hanains.http.HttpUtil;
import com.hanains.http.action.Action;
import com.hanains.mysite.dao.BoardDao;
import com.hanains.mysite.vo.BoardVo;
import com.hanains.mysite.vo.UserVo;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		System.out.println("진입");
		BoardDao dao = new BoardDao();
		BoardVo vo = new BoardVo();
		
		// 10Mbyte 제한
		int maxSize = 1024 * 1024 * 10;

		// 웹서버 컨테이너 경로
		String root = request.getSession().getServletContext().getRealPath("/");

		// 파일 저장 경로(ex : /home/tour/web/ROOT/upload)
		String savePath = root + "upload";

		// 업로드 파일명
		String uploadFile = "";

		// 실제 저장할 파일명
		String newFileName = "";

		int read = 0;
		byte[] buf = new byte[1024];
		FileInputStream fin = null;
		FileOutputStream fout = null;
		long currentTime = System.currentTimeMillis();
		SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");

		try {

			MultipartRequest multi = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new DefaultFileRenamePolicy());

			// 전송받은 parameter의 한글깨짐 방지
			String title= multi.getParameter("title");
			System.out.println(title);
			vo.setTitle(multi.getParameter("title"));
			vo.setContent(multi.getParameter("content"));
			vo.setGroup_no(dao.getMaxGroup() + 1);
			vo.setOrder_no(1);
			vo.setDepth(0);
			if (session != null) {
				vo.setMember_no(((UserVo) session.getAttribute("authUser")).getNo());
			}
			title = new String(title.getBytes("8859_1"), "UTF-8");

			// 파일업로드
			uploadFile = multi.getFilesystemName("uploadFile");

			// 실제 저장할 파일명(ex : 20140819151221.zip)
			newFileName = simDf.format(new Date(currentTime)) + "."
					+ uploadFile.substring(uploadFile.lastIndexOf(".") + 1);

			// 업로드된 파일 객체 생성
			File oldFile = new File(savePath + uploadFile);

			// 실제 저장될 파일 객체 생성
			File newFile = new File(savePath + newFileName);

			// 파일명 rename
			if (!oldFile.renameTo(newFile)) {

				// rename이 되지 않을경우 강제로 파일을 복사하고 기존파일은 삭제

				buf = new byte[1024];
				fin = new FileInputStream(oldFile);
				fout = new FileOutputStream(newFile);
				read = 0;
				while ((read = fin.read(buf, 0, buf.length)) != -1) {
					fout.write(buf, 0, read);
				}

				fin.close();
				fout.close();
				oldFile.delete();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		dao.insert(vo);

		HttpUtil.redirect(response, "/mysite/board");
	}

}
