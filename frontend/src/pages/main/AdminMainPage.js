import styled from 'styled-components';
import AdminPanel from '../../components/common/AdminPanel';
import AdminSideBarContainer from '../../containers/common/AdminSideBarContainer';

const MainPanel = styled(AdminPanel)``;

const AdminMainPage = () => {
  return (
    <>
      <AdminSideBarContainer />
      <MainPanel>관리자 메인페이지</MainPanel>
    </>
  );
};

export default AdminMainPage;
