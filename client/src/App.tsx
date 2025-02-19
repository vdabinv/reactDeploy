import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Header from "./Header";
import GalleryList from "./components/GalleryList";
import GalleryForm from "./components/GalleryForm";
import Footer from "./Footer";
import LoginForm from "./components/LoginForm";
import Counter from "ex5_dispatch/Counter";
import store from './ex5_dispatch/store';
import { Provider } from "react-redux";
function App() {
  return (
    <Provider store={store}>
    <Router>
      <Header/>
      {/* 라우터 목록 이동할 컴포넌트를 명시  */}
      <Routes>
        <Route path="/myapp0719/board" element={<div>게시판 컴포넌트</div>} />
        <Route path="/myapp0719/signup" element={<div>회원가입 컴포넌트</div>} />
        <Route path="/myapp0719/login" element={<LoginForm />} />
        <Route path="/myapp0719/products" element={<div>products 컴포넌트</div>} />
        <Route path="/myapp0719/gallery" element={<GalleryList/>} />
        <Route path="/myapp0719/gallery/new" element={<GalleryForm/>} />
        <Route path="/myapp0719/counter" element={<Counter />} />
      </Routes>
      <Footer/>
    </Router>
    </Provider>
  )
}
export default App;