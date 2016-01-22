
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">

<HTML>
<HEAD>
 
  <TITLE>Online Hotel Reservation</TITLE>
  <LINK REL=STYLESHEET
        HREF=<%=request.getContextPath() +"/resources/css/reservation.css"%>
        TYPE="text/css">
       
</HEAD>

<BODY>
<script>
    window.onload = function() {
    	 <% if(request.getAttribute("message")!=null) { %>
    	 alert( "<%=request.getAttribute("message")%>");
    	 <% } %>
    }
</script>
	<jsp:include page="menu.jsp"></jsp:include>


<BR>
<H1>Online Hotel Reservation</H1>

<CENTER>
 <form id="login" method="POST"
			action=<%=request.getContextPath() + "/createbooking"%>>
Room Type: <select name="roomtype">
  <option value="standardRoom">Standard Room</option>
  <option value="specialRoom">Special Room</option>
  <option value="exclusiveRoom">Exclusive Room</option>
</select><BR>
Window Direction: <select name="direction">
  <option value="east">EAST</option>
  <option value="west">WEST</option>
  <option value="north">NORTH</option>
   <option value="south">SOUTH</option>
</select><BR>
Start date (MM/DD/YY): 
  <INPUT TYPE="TEXT" NAME="startDate" SIZE=8><BR>
End date (MM/DD/YY): 
  <INPUT TYPE="TEXT" NAME="endDate" SIZE=8><BR>
<P>

<TABLE CELLSPACING=1>
<TR>
 <TH>&nbsp;<IMG SRC=<%=request.getContextPath() + "/resources/images/bed.gif"%> WIDTH=100 HEIGHT=85
                 ALIGN="MIDDLE" >&nbsp;
<TR>
  <TH><SMALL>
      <INPUT TYPE="SUBMIT" NAME="confirm" VALUE="Submit">
      </SMALL>
	   <TH> 
</TABLE>
</form>
</CENTER>

<BR>

</BODY>
</HTML>