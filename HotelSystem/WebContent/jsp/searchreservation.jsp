<%@page import="com.hotel.model.*"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <LINK REL=STYLESHEET
        HREF=<%=request.getContextPath() +"/resources/css/reservation.css"%>
        TYPE="text/css"></head>
<body>
	<jsp:include page="menu.jsp"></jsp:include>
	<div>

		<%
                Collection<Reservation> reservations = (Collection<Reservation>) session
                        .getAttribute("reservations");
            %>


		<div>
			<%
                    if (reservations != null) {
                %>
			<table
				style="margin-top: 50px; border-spacing: 0px; border-color: white">

				<tr valign="middle" height="50">
					<th style="width: 100px;">Start Date</th>
					<th style="width: 240px;">End Date</th>
					<th style="width: 150px;">Room Type</th>
					<th style="width: 200px;">Room Number</th>
					<th style="width: 350px;">Windows direction</th>
					<th style="width: 350px;">Reservation Number</th>

				</tr>


				<%
                        for (Reservation reservation: reservations) {
                    %>
				<tr>

					<td align="center" style="width: 100px;"><%=reservation.getStartDate()%>
					</td>
					<td align="center" style="width: 240px;"><%=reservation.getEndDate()%>
					</td>
					<td align="center" style="width: 150px;"><%=reservation.getRoomType()%>
					</td>
					<td align="center" style="width: 350px;"><%=reservation.getRoomNumber()%>
					</td>
					<td align="center" style="width: 350px;"><%=reservation.getDirection()%>
					</td>
					<td align="center" style="width: 350px;"><%=reservation.getReference()%>
					</td>

				</tr>
				<%}%>
				<%}%>

			</table>
		</div>
		<br> <br>
	</div>
</body>
</html>