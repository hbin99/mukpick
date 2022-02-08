import { Route } from 'react-router-dom';
import FoodMntPage from './pages/admin/FoodMntPage';
import SearchMntPage from './pages/admin/SearchMntPage';
import AdminMainPage from './pages/main/AdminMainPage';
import MainPage from './pages/main/MainPage';
import FindIdPage from './pages/member/FindIdPage';
import FindPasswordPage from './pages/member/FindPasswordPage';
import LoginPage from './pages/member/LoginPage';
import MyPage from './pages/member/MyPage';
import RegisterPage from './pages/member/RegisterPage';
import ResetPasswordPage from './pages/member/ResetPasswordPage';
function App() {
  return (
    <>
      {/* 메인페이지 */}
      <Route component={MainPage} path="/" exact />
      {/* 회원관리 */}
      <Route component={LoginPage} path="/login" />
      <Route component={MyPage} path="/mypage" />
      <Route component={RegisterPage} path="/register" />
      <Route component={FindPasswordPage} path="/find-password" />
      <Route component={FindIdPage} path="/find-id" />
      <Route component={ResetPasswordPage} path="/reset-password" />
      {/* 관리자 */}
      <Route component={AdminMainPage} path="/admin" exact />
      <Route component={SearchMntPage} path="/admin/search-mnt" />
      <Route component={FoodMntPage} path="/admin/food-mnt" />
    </>
  );
}

export default App;
