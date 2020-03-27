<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="headerDashboard.jsp"%>
</head>

<body>


<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="tabMenu.jsp" %>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="${pageContext.request.contextPath}/app/recipe/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="${pageContext.request.contextPath}/app/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">utwórz plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="${pageContext.request.contextPath}/app/recipe/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów:
                                <c:out value="${requestScope.numberRecipe}" default="Don't have"/></span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów:
                                <c:out value="${requestScope.get('numberPlan')}" default="Don't have"/></span>
                    </div>
                </div>
            </div>
            <div class="m-4 p-4 border-dashed">
                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span>
                    ${requestScope.plan.name}
                </h2>
                <c:forEach var="day" items="${requestScope.lastAddedPlan.planDetails}">
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${day.key}</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:set var="monday" value="poniedziałek"></c:set>
                            <c:set var="tuesday" value="wtorek"></c:set>
                            <c:set var="wednesday" value="środa"></c:set>
                            <c:set var="thursday" value="czwartek"></c:set>
                            <c:set var="friday" value="piątek"></c:set>
                            <c:set var="saturday" value="sobota"></c:set>
                            <c:set var="sunday" value="niedziela"></c:set>
                            <c:forEach var="item" items="${day.value}">
                                <c:if test="${item.dayName eq monday }">
                                    <tr class="d-flex">
                                        <td class="col-2">${item.mealName}</td>
                                        <td class="col-8">${item.recipeName}</td>
                                        <td class="col-2">
                                            <button onclick="location.href='<%=request.getContextPath()%>/app-details-schedules'"
                                                    class="btn btn-primary rounded-0">Szczegóły
                                            </button>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq tuesday }">
                                    <tr class="d-flex">
                                        <td class="col-2">${item.mealName}</td>
                                        <td class="col-8">${item.recipeName}</td>
                                        <td class="col-2">
                                            <button onclick="location.href='<%=request.getContextPath()%>/app-details-schedules'"
                                                    class="btn btn-primary rounded-0">Szczegóły
                                            </button>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq wednesday }">
                                    <tr class="d-flex">
                                        <td class="col-2">${item.mealName}</td>
                                        <td class="col-8">${item.recipeName}</td>
                                        <td class="col-2">
                                            <button onclick="location.href='<%=request.getContextPath()%>/app-details-schedules'"
                                                    class="btn btn-primary rounded-0">Szczegóły
                                            </button>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq friday }">
                                    <tr class="d-flex">
                                        <td class="col-2">${item.mealName}</td>
                                        <td class="col-8">${item.recipeName}</td>
                                        <td class="col-2">
                                            <button onclick="location.href='<%=request.getContextPath()%>/app-details-schedules'"
                                                    class="btn btn-primary rounded-0">Szczegóły
                                            </button>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq thursday }">
                                    <tr class="d-flex">
                                        <td class="col-2">${item.mealName}</td>
                                        <td class="col-8">${item.recipeName}</td>
                                        <td class="col-2">
                                            <button onclick="location.href='<%=request.getContextPath()%>/app-details-schedules'"
                                                    class="btn btn-primary rounded-0">Szczegóły
                                            </button>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq saturday }">
                                    <tr class="d-flex">
                                        <td class="col-2">${item.mealName}</td>
                                        <td class="col-8">${item.recipeName}</td>
                                        <td class="col-2">
                                            <button onclick="location.href='<%=request.getContextPath()%>/app-details-schedules'"
                                                    class="btn btn-primary rounded-0">Szczegóły
                                            </button>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq sunday }">
                                    <tr class="d-flex">
                                        <td class="col-2">${item.mealName}</td>
                                        <td class="col-8">${item.recipeName}</td>
                                        <td class="col-2">
                                            <button onclick="location.href='<%=request.getContextPath()%>/app-details-schedules'"
                                                    class="btn btn-primary rounded-0">Szczegóły
                                            </button>
                                        </td>
                                    </tr>
                                </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:forEach>
            </div>
        </div>
    </div>
</section>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>