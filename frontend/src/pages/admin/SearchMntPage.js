import styled from 'styled-components';
import AdminPanel from '../../components/common/AdminPanel';
import SearchMntContainer from '../../containers/admin/SearchMntContainer';
import AdminSideBarContainer from '../../containers/common/AdminSideBarContainer';

const MainPanel = styled(AdminPanel)``;

const SearchMntPage = () => {
  return (
    <>
      <AdminSideBarContainer />
      <MainPanel>
        <SearchMntContainer />
      </MainPanel>
    </>
  );
};

export default SearchMntPage;
