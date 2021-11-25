import styled from 'styled-components';
import AdminPanel from '../../components/common/AdminPanel';
import FoodMntContainer from '../../containers/admin/FoodMntContainer';
import AdminSideBarContainer from '../../containers/common/AdminSideBarContainer';

const MainPanel = styled(AdminPanel)``;

const FoodMntPage = () => {
  return (
    <>
      <AdminSideBarContainer />
      <MainPanel>
        <FoodMntContainer />
      </MainPanel>
    </>
  );
};

export default FoodMntPage;
