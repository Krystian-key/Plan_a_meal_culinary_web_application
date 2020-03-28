<%--
  Created by IntelliJ IDEA.
  User: soul
  Date: 3/14/20
  Time: 4:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="header.jsp"%>
</head>
<body>

<section class="padding-large bg-light">

    <div id="carouselExampleControls" class="carousel slide main-slider" data-ride="carousel">
        <div class="carousel slider container">
           <div class="carousel-item active">
                <div class="container w-50 d-flex">
                    <div class="carousel-caption d-block">
                        <h1>Maria Iksińska autorka bestsellerowej książki</h1>
                        <h3>Zaprasza do wypróbowania swoich nowatorskich przepisów.</h3>
                    </div>
                </div>
            </div>
            <c:forEach items="${requestScope.id}" var="about">
                <div class="carousel-item">
                    <div class="container w-75 d-flex">
                        <div class="carousel-caption d-block">
                            <h1>${about.title}</h1>
                            <h3>${about.description}</h3>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</section>
<%@include file="footer.jsp"%>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>
</html>