package program.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import program.dao.ProgramDAO;
import program.dto.ProgramDTO;

@WebServlet("/program.do")
public class ProgramListController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        ProgramDAO dao = new ProgramDAO();
        List<ProgramDTO> programList = dao.selectAllPrograms();
        
        dao.close();

        request.setAttribute("programList", programList);
        request.getRequestDispatcher("program.jsp").forward(request, response);
    }
}
