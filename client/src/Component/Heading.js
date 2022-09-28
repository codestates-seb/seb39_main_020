import React from 'react'
// useState안씀
import { Link } from "react-router-dom"
import { Navbar, Container, Nav } from 'react-bootstrap';

function Heading() {
  return (
    <Navbar bg="dark" expand="lg" variant="dark">
      <Container>
        <Navbar.Brand href="/">부캠도감</Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">

                <Link to="/institutes" style={{ color: "white", textDecoration: "none", marginRight: "10px"}}>Institutes</Link>
            
            
                <Link to="/upload" style={{ color: "white", textDecoration: "none", marginRight: "10px" }}>upload</Link>
            
            
                <Link to="/list" style={{ color: "white", textDecoration: "none", marginRight: "10px"}}>list</Link>  


                <Link to="/community" style={{ color: "white", textDecoration: "none", marginRight: "10px"}}>community</Link>  


                <Link to="/qna" style={{ color: "white", textDecoration: "none", marginRight: "10px"}}>qna</Link>  
            
            
                <Link to="/login" style={{ color: "white", textDecoration: "none", marginRight: "10px"}}>Login</Link>  
            
            
                <Link to="/register" style={{ color: "white", textDecoration: "none", marginRight: "10px"}}>Register</Link>  
            
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default Heading;