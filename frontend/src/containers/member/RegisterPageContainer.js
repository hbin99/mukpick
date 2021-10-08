import { withRouter } from 'react-router-dom';
import Register from "../../components/member/Register";
const RegisterPageContainer = () => {

  return (
    <>
          <Register/>
    </>
  );
};

export default withRouter(RegisterPageContainer);
