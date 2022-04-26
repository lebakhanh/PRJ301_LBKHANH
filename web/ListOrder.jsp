<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>
            Admin Dashboard
        </title>
        <link rel="shortcut icon" href="/images/logo-mb.png" type="image/png">
        <!-- GOOGLE FONT -->
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
        <!-- BOXICONS -->
        <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
        <!-- APP CSS -->
        <link rel="stylesheet" href="css/grid.css">
        <link rel="stylesheet" href="css/app.css">
        <script type="text/javascript">
            function doDelete(id){
                if(confirm("Are u sure to delete order with id="+id+" ?")){
                    window.location="delete?id="+id;
                }
            }
        </script>
    </head>

    <body>

        <jsp:include flush="true" page="module/header-admin.jsp"/>

        <!-- MAIN CONTENT -->
        <div class="main">
            <div class="main-header">
                <div class="mobile-toggle" id="mobile-toggle">
                    <i class='bx bx-menu-alt-right'></i>
                </div>
                <div class="main-title">
                    dashboard
                </div>
            </div>
            <div class="main-content">
                <div class="row">
                    <div class="col-md-5">                                    
                                <form id="f" action="list-order?sort=${requestScope.sort}?page=${requestScope.page}">
                                    <select name="sort" onchange="document.getElementById('f').submit()"> 
                                        <option>All list</option>
                                        <option value="1" <c:if test="${sort==1}">
                                                selected
                                            </c:if> >Pending Order</option>
                                        <option value="2" <c:if test="${sort==2}">
                                                selected
                                            </c:if> >Shipping Order</option>
                                        <option value="3" <c:if test="${sort==3}">
                                                selected
                                            </c:if> >Reject Order</option>                                      
                                    </select>
                                </form>

                            </div>
                   <div class="col-12">
                        <!-- ORDERS TABLE -->
                        <div class="box">
                            <div class="box-header">
                                Recent orders
                            </div>
                            <div class="box-body overflow-scroll">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Full Name</th>
                                            <th>Date</th>   
                                            <th>Phone Number</th>
                                            <th>Email</th>
                                            <th>Addres</th>
                                            <th>Note</th>
                                            <th>Status</th>
                                            
                                            <th>Total Money</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.orderlist}" var="order">
                                            <tr>
                                            <td>#${order.id}</td>
                                            <td>
                                                <div class="order-owner">
                                                   
                                                    <span>${order.fullname}</span>
                                                </div>
                                            </td>
                                            <td>${order.date}</td>
                                            <td>${order.phone_number}</td>
                                            <td>${order.email}</td>
                                            <td>${order.address}</td>
                                            <td>${order.note}</td>
                                            <c:if test="${order.status==0}">
                                            <td>
                                                <div class="payment-status payment-pending">
                                                    <div class="dot"></div>
                                                    <span>Pending</span>
                                                </div
                                            </td>
                                            </c:if>
                                            <c:if test="${order.status==1}">
                                                <td>
                                                     <div class="payment-status payment-paid">
                                                <div class="dot"></div>
                                                <span>Shipping</span>
                                            </div>
                                                </td>
                                           
                                            </c:if>
                                                <c:if test="${order.status==2}">
                                                <td>
                                                     <div class="payment-status payment-status bg-danger color d-inline-block rounded-circle mr-2"> X
                                                <div class="dot"></div>
                                                <span>Reject</span>
                                            </div>
                                                </td>
                                           
                                            </c:if>
                                                <td>$${order.total_money}</td>
                                                        <td>
                                                    <span class="order-status order-ready">
                                                    <a href="#" onclick="doDelete('${order.id}')">Delete
                                                </span>
                                                    </td>
                                                
                                            
                                            <c:if test="${order.status==0}">
                                                <td>
                                                    <a href="accept?id=${order.id}">
                                                        <span class="order-status order-ready">
                                                    Accept
                                                </span>
                                                    </a>
                                                
                                            </td>
                                            <td>
                                                    <a href="reject?id=${order.id}">
                                                        <span class="order-status order-ready">
                                                    Reject
                                                </span>
                                                    </a>
                                                
                                            </td>
                                            </c:if>
                                            
                                        </tr>
                                        </c:forEach>
                                        
                                       
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <!-- END ORDERS TABLE -->
                    </div>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT -->

        <div class="overlay"></div>

        <!-- SCRIPT -->
        <!-- APEX CHART -->
        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <!-- APP JS -->
        <script src="js/app.js"></script>

    </body>

</html>