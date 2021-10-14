import HeaderContainer from "../../containers/common/HeaderContainer";
import FindUserPageContainer from '../../containers/member/FindUserPageContainer';

const FindPasswordPage = () => {
    return (
        <>
            <HeaderContainer/>
            <FindUserPageContainer type = "findPassword"/>
        </>
    );
}


export default FindPasswordPage;