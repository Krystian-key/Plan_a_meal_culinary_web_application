<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">

<head>
    <%@include file="headerDashboard.jsp"%>
</head>

<body>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="tabMenu.jsp" %>
        <div class="m-4 p-3 width-medium">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">

                <form action="${pageContext.request.contextPath}/app/recipe/plan/add" method="post">
                    <div class="row border-bottom border-3 p-1 m-1">
                        <div class="col noPadding">
                            <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU </h3>
                        </div>
                        <div class="col d-flex justify-content-end mb-2 noPadding">
                            <button type="submit" class="btn btn-success rounded-4 pt-0 pb-0 pr-4 pl-4">Wyślij</button>

                        </div>
                    </div>

                    <div class="schedules-content">

                        <div class="form-group row">
                            <label for="planList" class="col-sm-2 label-size col-form-label">
                                Wybierz plan
                            </label>
                            <div class="col-sm-3">
                                <select class="form-control" id="planList" name="planList">
                                    <c:forEach var="plan" items="${planList}">
                                        <option value="${plan.id}">
                                                ${plan.name}
                                        </option>
                                        <br>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="meal" class="col-sm-2 label-size col-form-label">
                                meal_name
                            </label>
                            <div class="col-sm-3">
                                <select class="form-control" id="meal" name="meal">
                                    <option value="Śniadanie">Śniadanie</option>
                                    <option value="Drugie Śniadanie">Drugie Śniadanie</option>
                                    <option value="Lunch">Lunch</option>
                                    <option value="Obiad">Obiad</option>
                                    <option value="Podwieczorek">Podwieczorek</option>
                                    <option value="Przekąska">Przekąska</option>
                                    <option value="Kolacja">Kolacja</option>
                                </select>
                            </div>
                        </div>
                        <%--      for later use
                            <div class="form-group row">
                                      <label for="number" class="col-sm-2 label-size col-form-label">
                                          numer posiłku baza danych display_order
                                      </label>
                                      <div class="col-sm-2">
                                          <input type="text" class="form-control" value="" id="number" placeholder="Numer posiłki">
                                      </div>
                                  </div>--%>
                        <div class="form-group row">
                            <label for="recipie" class="col-sm-2 label-size col-form-label">
                                Przepis
                            </label>
                            <div class="col-sm-4">
                                <select class="form-control" id="recipie" name="recipie">
                                    <c:forEach var="list" items="${recipeList}">
                                        <option value="${list.id}">
                                                ${list.name}
                                        </option>
                                        <br>
                                    </c:forEach>

                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="day" class="col-sm-2 label-size col-form-label">
                                Dzień
                            </label>
                            <div class="col-sm-2">
                                <select class="form-control" id="day" name="day">
                                    <c:forEach var="day" items="${dayNameList}">
                                        <option value="${day.id}">
                                                ${day.name}
                                        </option>
                                        <br>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                    </div>
                </form>
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