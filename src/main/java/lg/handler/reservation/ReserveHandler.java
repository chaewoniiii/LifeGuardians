package lg.handler.reservation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lg.handler.Handler;
import lg.hospital.Hospital;
import lg.hospital.HospitalService;
import lg.member.Member;
import lg.member.MemberService;
import lg.reservation.Reservation;
import lg.reservation.ReservationService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

public class ReserveHandler implements Handler {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String page = "";
        // 병원 상세페이지에서 예약하기 누르면 환자이름을 받아서 reserve_detail에 보내줌
//        MemberService memberservice = new MemberService();
//        Member member = memberservice.getMemberByMemberid(member_id);
//        request.setAttribute("member", member);
//        String hpid = request.getParameter("hpid");

        if(request.getMethod().equals("GET")){ // 병원예약버튼 클릭시
            //ing
            // TODO : name 보내기
//            int member_id = (int) request.getSession().getAttribute("member_id");
//            System.out.println(member_id);
//
//            MemberService memberservice = new MemberService();
//            Member member = memberservice.getMemberByMemberid(member_id);
//            System.out.println(member.getName());
//            String resultmember = objectToJson(member);
//            try {
//                response.getWriter().write(resultmember);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            String name = member.getName();
//
//            request.setAttribute("name",name);
//            System.out.println(name);
//            request.setAttribute("member",member);

            /* hpid -> hospitalDto를 json으로 보냄 */
            String hpid = request.getParameter("hpid");
            HospitalService hospitalservice = new HospitalService();
            Hospital hospital = hospitalservice.getHospitalByHpid(hpid);
            String result = objectToJson(hospital);
            try {
                response.getWriter().write(result);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        } else { // post방식 : 예약확인 클릭시 -> db에 값을 넘겨줌
            System.out.println("1");
            String reserveTime = request.getParameter("datetimepicker");
//            System.out.println(reserveTime);
            String symptoms = request.getParameter("symptoms");
            String status = "예약완료"; // default status : 예약완료
//
//            Reservation reservation = Reservation.builder().member_id(member_id).hpid(hpid).reserveTime(reserveTime).symptoms(symptoms).status(status).build();
//            ReservationService reservationservice = new ReservationService();
//            reservationservice.addReservation(reservation);
//            page = "/page/reservation/reserve.jsp"; //? 홈페이지로? 아님 다시 병원 상세페이지로?
            page = "redirect:/";

            return page;
        }

    }

    private String objectToJson(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
