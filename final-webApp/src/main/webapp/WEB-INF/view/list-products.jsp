<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>
            Product List
        </title>
        <link type="text/css"
              rel="stylesheet"
              href="${pageContext.request.contextPath}/resources/css/style.css" />
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                 <h2>E Commerce Featured Products</h2>
            </div>
        </div>
        <div id="container">
            <div id="content">
                <input type="button" value="Add Product" onClick="window.location.href='showFormForAdd'; return false;"
                 class="add-button" />
                <!-- add out html table here -->
                <table>
                    <tr>

                        <th>Product Name</th>
                        <th>Product Details</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>

                    <!-- loop over and print our products -->
                     <c:forEach var="tempProduct" items="${products}">
                        <c:url var="updateLink" value="/product/showFormForUpdate">
                            <c:param name="productId" value="${tempProduct.id}" />
                        </c:url>

                        <c:url var="deleteLink" value="/product/delete">
                            <c:param name="productId" value="${tempProduct.id}" />
                        </c:url>


                        <tr>

                            <td>${tempProduct.name}</td>
                            <td>${tempProduct.description}</td>
                            <td>${tempProduct.price}</td>
                            <td>
                                <a href="${updateLink}">Update</a>
                                |
                                <a href="${deleteLink}"
                                    OnClick="if (!(confirm('Are you sure you want to delete this product? '))) return false">Delete</a>
                            </td>
                        </tr>
                     </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
