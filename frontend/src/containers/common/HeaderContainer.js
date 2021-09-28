import Header from '../../components/common/Header';

const HeaderContainer = ({ isAdminMode }) => {
  const onLogout = () => {};
  return <Header onLogout={onLogout} isAdminMode={isAdminMode} />;
};

export default HeaderContainer;
