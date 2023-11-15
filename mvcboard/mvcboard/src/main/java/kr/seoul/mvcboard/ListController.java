package kr.seoul.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.BoardPage;

@WebServlet("/mvcboard")
public class ListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       // DAO 생성
       MVCBoardDAO dao = new MVCBoardDAO();

        // 뷰에 전달할 매개변수 저장용 맵 생성
        Map<String, Object> map = new HashMap<String, Object>();
        String searchField = req.getParameter("searchRield");
        String searchWord = req.getParameter("searchWord");
        if (searchField != null) {
        	map.put("searchField", searchField);
        	map.put("searchWord", searchWord);
        }
        
        int totalcount = dao.selectCount(map);
        
        ServletContext application = getServletContext();
        int pageSize = Integer.parseInt(application.getInitParameter("POST_PER_PAGE"));
        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
        
        int pageNum = 1;
        String pageTemp = req.getParameter("pageNum");
        if(pageTemp != null && !pageTemp.equals("")) {
        	pageNum = Integer.parseInt(pageTemp);
        }
        int start = (pageNum - 1) * pageSize + 1;
        int end = pageNum * pageSize;
        map.put("start", start);
        map.put("end", end);
        
        List<MVCBoardDTO> boardlists = dao.selectListPage(map);
        dao.close();
        
        String pagingimg = BoardPage.pagingStr(totalcount, pageSize, blockPage, pageNum, "../mvcboard/list.do");
        
        map.put("pagingImg", pagingimg);
        map.put("totalCount", totalcount);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        
        req.setAttribute("boardLists", boardlists);
        req.setAttribute("map", map);
        req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req, resp);
        
    }
}
