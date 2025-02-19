// src/Header.tsx
import React from 'react'
//import { Link } from 'react-router-dom'
import { Link, useNavigate } from 'react-router-dom';
const Header = () => {

  //추가 
  const navigate = useNavigate();
  const username = localStorage.getItem('username');
  const isLoggedIn = !!username;

  const logout = () => {
    localStorage.removeItem('username');
    localStorage.removeItem('token');
    navigate('/');
  };

  //


  return (
    <header>
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">ICT STUDY</Link>
        <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link" to="/myapp0719/board">게시판</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/myapp0719/signup">회원가입</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/myapp0719/counter">Redux예제</Link>
            </li>
            {/* <li className="nav-item">
              <Link className="nav-link" to="/myapp0719/login">로그인</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/myapp0719/products">상품목록</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/myapp0719/gallery">갤러리</Link>
            </li> */}
             {isLoggedIn ? (
                <>
                  <li className="nav-item">
                    <Link className="nav-link" to="/myapp0719/products">상품목록</Link>
                  </li>
                  <li className="nav-item">
                    <Link className="nav-link" to="/myapp0719/gallery">갤러리</Link>
                  </li>
                  <li className="nav-item">
                    <button className="nav-link btn" onClick={logout}>로그아웃</button>
                  </li>
                </>
              ) : (
                <li className="nav-item">
                  <Link className="nav-link" to="/myapp0719/login">로그인</Link>
                </li>
              )}
          </ul>
        </div>
      </div>
    </nav>
  </header>
  )
}

export default Header