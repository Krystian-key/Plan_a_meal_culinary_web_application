<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="headerDashboard.jsp"%>
</head>

<body>
<section class="dashboard-section">
    <form method="post" action="${pageContext.request.contextPath}/app/plan/edit">
    <div class="row dashboard-nowrap">
        <%@include file="tabMenu.jsp" %>
        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <input type="submit" value="Edytuj" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">
                        <a href="${pageContext.request.contextPath}/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4" style="margin-left: 10px">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                         <%--  <p class="schedules-text">${requestScope.plan.name}</p>--%>
                                <input type="text" name="planName" placeholder="${requestScope.plan.name}">
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
<%--                                    ${requestScope.plan.description}--%>
                                    <input type="text" name="planDescription" placeholder="${requestScope.plan.description}">
                                </p>
                            </div>
                        </div>
                    </div>
                    <c:forEach var="day" items="${requestScope.recipePlanDao.planDetails}">
                        <table class="table">
                            <thead>
                            <tr class="d-flex">
                                <th class="col-2">${day.key}</th>
                                <th class="col-7"></th>
                                <th class="col-1"></th>
                                <th class="col-2"></th>
                            </tr>
                            </thead>
                            <tbody class="text-color-lighter">
                            <c:set var="monday" value="poniedziałek"></c:set>
                            <c:set var="tuesday" value="wtorek"></c:set>
                            <c:set var="wednesday" value="środa"></c:set>
                            <c:set var="thursday" value="czwartek"></c:set>
                            <c:set var="friday" value="piątek"></c:set>
                            <c:set var="saturday" value="sobota"></c:set>
                            <c:set var="sunday" value="niedziela"></c:set>
                            <c:forEach var="item" items="${day.value}">
                                <c:if test="${item.dayName eq monday}">
                                    <tr class="d-flex">
                                       <%-- <td class="col-2">${item.mealName}</td>
                                        <td class="col-7">${item.recipeName}</td>--%>
                                        <select class="form-control"  name="recipeMonday">
                                            <option value="${item.mealName}">
                                                    ${item.recipeName}
                                            </option>
                                            <c:forEach var="recipe" items="${allRecipe}">
                                                <option value="${recipe.id}">
                                                        ${recipe.name}
                                                </option>
                                                <br>
                                            </c:forEach>

                                        </select>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq tuesday}">
                                    <tr class="d-flex">
                                        <select class="form-control" name="recipeTuesday">
                                            <option value="${item.mealName}">
                                                    ${item.recipeName}
                                            </option>
                                            <c:forEach var="recipe" items="${allRecipe}">
                                                <option value="${recipe.id}">
                                                        ${recipe.name}
                                                </option>
                                                <br>
                                            </c:forEach>

                                        </select>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq wednesday}">
                                    <tr class="d-flex">
                                        <select class="form-control" name="recipeWednesday">
                                            <option value="${item.mealName}">
                                                    ${item.recipeName}
                                            </option>
                                            <c:forEach var="recipe" items="${allRecipe}">
                                                <option value="${recipe.id}">
                                                        ${recipe.name}
                                                </option>
                                                <br>
                                            </c:forEach>

                                        </select>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq thursday}">
                                    <tr class="d-flex">
                                        <select class="form-control" name="recipeThursday">
                                            <option value="${item.mealName}">
                                                    ${item.recipeName}
                                            </option>
                                            <c:forEach var="recipe" items="${allRecipe}">
                                                <option value="${recipe.id}">
                                                        ${recipe.name}
                                                </option>
                                                <br>
                                            </c:forEach>

                                        </select>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq friday}">
                                    <tr class="d-flex">
                                        <select class="form-control" name="recipeFriday">
                                            <option value="${item.mealName}">
                                                    ${item.recipeName}
                                            </option>
                                            <c:forEach var="recipe" items="${allRecipe}">
                                                <option value="${recipe.id}">
                                                        ${recipe.name}
                                                </option>
                                                <br>
                                            </c:forEach>

                                        </select>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq saturday}">
                                    <tr class="d-flex">
                                        <select class="form-control" name="recipeSaturday">
                                            <option value="${item.mealName}">
                                                    ${item.recipeName}
                                            </option>
                                            <c:forEach var="recipe" items="${allRecipe}">
                                                <option value="${recipe.id}">
                                                        ${recipe.name}
                                                </option>
                                                <br>
                                            </c:forEach>

                                        </select>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${item.dayName eq sunday}">
                                    <tr class="d-flex">
                                        <select class="form-control" name="recipeSunday">
                                            <option value="${item.mealName}">
                                                    ${item.recipeName}
                                            </option>
                                            <c:forEach var="recipe" items="${allRecipe}">
                                                <option value="${recipe.id}">
                                                        ${recipe.name}
                                                </option>
                                                <br>
                                            </c:forEach>

                                        </select>

                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
        </form>
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