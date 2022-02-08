import Header from '../../components/common/Header';
// import {useSelector} from "react-redux";

const HeaderContainer = ({ isAdminMode }) => {
  const onLogout = () => {};
  //const {user} = useSelector(({user}) => ({user : user.user}));
  return <Header
      onLogout={onLogout}
      isAdminMode={isAdminMode}
     // user ={user}
  />;
};

export default HeaderContainer;
