<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@include file="seller_header.jsp" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		
    </head>
	<body>
		

		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">Products</h3>
						<ul class="breadcrumb-tree">
							<li><a href="#">Home</a></li>
							<li class="active">Add Product</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">					
					
					<%
						if(request.getAttribute("msg")!=null)
						{
							out.println(request.getAttribute("msg"));
						}
						
					%>
					
					<div class="col-md-7">
						<!-- Billing Details -->
						
						<%
							int pid = Integer.parseInt(request.getParameter("pid"));
							Product p = ProductDao.getProductById(pid);
						
						%>
						
						<form name="addproduct" action="ProductController" method="post" enctype="multipart/form-data">
						<input type="hidden" name="uid" value="<%=u.getUid()%>">					
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Update Product</h3>
							</div>
							
							<div class="form-group">
								<b>Product Category :</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								
								<%
									if(p.getProduct_category().equals("camera"))
									{
								%>
									<input  type="radio" name="product_category" value="camera" checked="checked">Camera&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="product_category" value="mobile">Mobile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="product_category" value="laptop">Laptop
									
								<%
									}
									else if(p.getProduct_category().equals("mobile"))
									{
								%>
									<input  type="radio" name="product_category" value="camera" >Camera&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="product_category" value="mobile" checked="checked">Mobile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="product_category" value="laptop">Laptop
								<%
										
									}
									else if(p.getProduct_category().equals("laptop"))
									{
								%>
									<input  type="radio" name="product_category" value="camera" >Camera&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="product_category" value="mobile" >Mobile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="product_category" value="laptop" checked="checked">Laptop	
								<%
										
									}
									else
									{
								%>
									<input  type="radio" name="product_category" value="camera" >Camera&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="product_category" value="mobile" >Mobile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input  type="radio" name="product_category" value="laptop" >Laptop	
								<%		
									}
								
								
								%>
																
								
							</div>								
							<div class="form-group">
								<input class="input" type="text" name="product_name" value="<%=p.getProduct_name()%>">
							</div>
							<div class="form-group">
								<input class="input" type="text" name="product_model" value="<%=p.getProduct_model()%>">
							</div>
							<div class="form-group">
								<input class="input" type="text" name="product_price" value="<%=p.getProduct_price()%>">
							</div>						
											
							<div class="form-group">
								<textarea class="input" rows="10" cols="15" name="product_desc" placeholder="Product Description"> <%=p.getProduct_desc()%></textarea>								
							</div>							
							
							<div class="form-group">
								<img src="Product_Images/<%=p.getProduct_image()%>" width="200px" height="250px">						
								
							</div>
							
							<div class="form-group">
								<input class="btn btn-danger" type="submit" name="action" value="Update Product">
							</div>
						</div>
						<!-- /Billing Details -->
					</form>
						

					

					
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		

		<!-- FOOTER -->
		<footer id="footer">
			<!-- top footer -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">About Us</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
									<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
									<li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
									<li><a href="#">Hot deals</a></li>
									<li><a href="#">Laptops</a></li>
									<li><a href="#">Smartphones</a></li>
									<li><a href="#">Cameras</a></li>
									<li><a href="#">Accessories</a></li>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Information</h3>
								<ul class="footer-links">
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact Us</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Orders and Returns</a></li>
									<li><a href="#">Terms & Conditions</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="#">My Account</a></li>
									<li><a href="#">View Cart</a></li>
									<li><a href="#">Wishlist</a></li>
									<li><a href="#">Track My Order</a></li>
									<li><a href="#">Help</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /top footer -->

			<!-- bottom footer -->
			<div id="bottom-footer" class="section">
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-12 text-center">
							<ul class="footer-payments">
								<li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
								<li><a href="#"><i class="fa fa-credit-card"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
								<li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
							</ul>
							<span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>
						</div>
					</div>
						<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /bottom footer -->
		</footer>
		<!-- /FOOTER -->

		<!-- jQuery Plugins -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/slick.min.js"></script>
		<script src="js/nouislider.min.js"></script>
		<script src="js/jquery.zoom.min.js"></script>
		<script src="js/main.js"></script>

	</body>
</html>
