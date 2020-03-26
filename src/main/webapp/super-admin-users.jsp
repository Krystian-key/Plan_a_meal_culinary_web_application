<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html lang="en">

<head>
    <%@include file="headerDashboard.jsp"%>
</head>
<body>
    <section class="dashboard-section">
        <div class="row dashboard-nowrap">
            <%@include file="tabMenu.jsp" %>

            <div class="m-4 p-3 width-medium">
                <div class="m-4 p-3 border-dashed view-height">

                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <a href="#" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                        </div>
                    </div>

                    <div class="schedules-content">
                        <table class="table">
                            <thead>
                                <tr class="d-flex">
                                    <th class="col-1">ID</th>
                                    <th class="col-3">IMIĘ</th>
                                    <th class="col-6">NAZWISKO</th>
                                    <th class="col-2 center">AKCJE</th>
                                </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:forEach var="user" items="${requestScope.users}">
                                <tr class="d-flex">
                                    <td class="col-1">${user.id}</td>
                                    <td class="col-3">${user.firstName}</td>
                                    <td class="col-6">${user.lastName}</td>
                                    <td class="col-2 center">
                                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                                    </td>
                                </tr>
                            </c:forEach>
<%--                                <tr class="d-flex">--%>
<%--                                    <td class="col-1">1</td>--%>
<%--                                    <td class="col-3">Marek</td>--%>
<%--                                    <td class="col-6">Marecki</td>--%>
<%--                                    <td class="col-2 center">--%>
<%--                                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>--%>
<%--                                    </td>--%>
<%--                                </tr>--%>
<%--                                <tr class="d-flex">--%>
<%--                                    <td class="col-1">2</td>--%>
<%--                                    <td class="col-3">Marek</td>--%>
<%--                                    <td class="col-6">Marecki</td>--%>
<%--                                    <td class="col-2 center">--%>
<%--                                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>--%>
<%--                                    </td>--%>
<%--                                </tr>--%>
<%--                                <tr class="d-flex">--%>
<%--                                    <td class="col-1">3</td>--%>
<%--                                    <td class="col-3">Marek</td>--%>
<%--                                    <td class="col-6">Marecki</td>--%>
<%--                                    <td class="col-2 center">--%>
<%--                                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>--%>
<%--                                    </td>--%>
<%--                                </tr>--%>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    </section>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>