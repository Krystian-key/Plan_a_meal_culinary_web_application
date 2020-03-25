<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <%@include file="headerDashboard.jsp"%>
    <style>
        h3 {
            text-align: center;
        }
    </style>
</head>

<body>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <%@include file="tabMenu.jsp" %>

        <div class="m-4 p-3 width-medium text-color-darker">
            <div class="dashboard-content border-dashed p-3 m-4 view-height">
                <!-- fix action, method -->
                <!-- add name attribute for all inputs -->

                    <div class="mt-4 ml-4 mr-4">
                        <div class="row border-bottom border-3">
                            <div class="col"><h3 class="color-header text-uppercase">Czy na pewno chcesz usunąć przepis z planu?</h3></div>
                        </div>
                        <div class="row d-flex">
                            <table class="table borderless">
                                <thead>
                                <tr class="d-flex">
                                    <th class="col-4"></th>
                                    <th class="col-2">
                                        <form action="${pageContext.request.contextPath}/app/plan/delete-recipe?delateRecipPlanId=${requestScope.delateRecipPlanId}&deletePlanId=${requestScope.deletePlanId}" method="post">
                                            <button type="submit" class="btn btn-danger rounded-25 text-light m-1">Ok</button>
                                        </form>
                                    </th>
                                    <th class="col-1"></th>
                                    <th class="col-2">
                                        <a href="${pageContext.request.contextPath}/app/plan/details?id=${requestScope.delateRecipPlanId}" class="btn btn-warning rounded-25 text-light m-1">Anuluj</a>
                                    </th>
                                    <th class="col-3"></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
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