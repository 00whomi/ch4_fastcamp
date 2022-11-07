//package com.fastcampus.ch4.controller;
//
//import com.fastcampus.ch4.domain.BoardDto;
//import com.fastcampus.ch4.domain.PageHandler;
//import com.fastcampus.ch4.service.BoardService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//@RequestMapping("/board")
//public class BoardController_old {
//    @Autowired
//    BoardService boardService;
//
//    @PostMapping("/modify")
//    public String modify(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
//        String writer = (String) session.getAttribute("id");
//        boardDto.setWriter(writer);
//        try {
//            int rowCnt = boardService.modify(boardDto);     // insert
//            if(rowCnt!=1)
//                throw new Exception("Modify failed");
//
//            rattr.addFlashAttribute("msg", "MDF_OK");
//
//            return "redirect:/board/list";    // 수정 후 원래의 글이 있던 페이지를 보여주는 코드로 수정해보기!!
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            m.addAttribute(boardDto);      // 예외 발생했을 때 작성중인 내용을 지우지 않고 다시 보여준다.
//            m.addAttribute("msg", "MDF_ERR");
//            return "board_old";
//        }
//    }
//    @PostMapping("/write")
//    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
//        String writer = (String) session.getAttribute("id");
//        boardDto.setWriter(writer);
//        try {
//            int rowCnt = boardService.write(boardDto);     // insert
//            if(rowCnt!=1)
//                throw new Exception("Write failed");
//
//            rattr.addFlashAttribute("msg", "WRT_OK");
//
//                return "redirect:/board/list";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            m.addAttribute(boardDto);      // 예외 발생했을 때 작성중인 내용을 지우지 않고 다시 보여준다.
//            m.addAttribute("msg", "WRT_ERR");
//            return "board_old";
//        }
//    }
//    @GetMapping("/write")
//    public String write(Model m){
//        m.addAttribute("mode", "new");
//        return "board_old";  // 읽기와 쓰기에 모두 사용하므로, 쓰기에 사용할 때는 mode=new로 작성한다.
//    }
//
//    @PostMapping("/remove")
//    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr){
//
//        String writer = (String)session.getAttribute("id");
//        try {
//            m.addAttribute("page", page);
//            m.addAttribute("pageSize", pageSize);
//            int rowCnt = boardService.remove(bno, writer);
//
//            if(rowCnt!=1)
//                throw new Exception("board remove error");
//
//            rattr.addFlashAttribute("msg", "DEL_OK");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            rattr.addFlashAttribute("msg", "DEL_ERR");
//
//        }
//
//        return "redirect:/board/list";
//    }
//
//    @GetMapping("/read")
//    public String read(Integer bno, Integer page, Integer pageSize, Model m){
//        try {
//            BoardDto boardDto = boardService.read(bno);
////            m.addAttribute("boardDto", boardDto);  // 아래 문장과 동일
//            m.addAttribute(boardDto);
//            m.addAttribute("page", page);
//            m.addAttribute("pageSize", pageSize);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "board_old";
//    }
//
//
//    @GetMapping("/list")
//    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
//        if(!loginCheck(request))
//            return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
//
//        if(page==null) page =1;
//        if(pageSize==null) pageSize=10;
//
//        try {
//            int totalCnt = boardService.getCount();
//            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
//
//            Map map = new HashMap();
//            map.put("offset", (page-1)*pageSize);
//            map.put("pageSize", pageSize);
//
//           List<BoardDto> list = boardService.getPage(map);
//           m.addAttribute("List", list);
//           m.addAttribute("ph", pageHandler);
//           m.addAttribute("page", page);
//           m.addAttribute("pageSize", pageSize);
//        } catch (Exception e) {
//            e.printStackTrace();
////            throw new RuntimeException(e);
//        }
//
//
//        return "boardList_old"; // 로그인을 한 상태이면, 게시판 화면으로 이동
//    }
//
//    private boolean loginCheck(HttpServletRequest request) {
//        // 1. 세션을 얻어서
//        HttpSession session = request.getSession();
//        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
//        return session.getAttribute("id")!=null;
//    }
//}