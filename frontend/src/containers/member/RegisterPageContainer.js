import { withRouter } from 'react-router-dom';
import RegisterForm from "../../components/member/Register";
import {useDispatch, useSelector} from "react-redux";
import {chengeField, registerData, initializeForm, findId} from "../../modules/member/findId";
import {useEffect} from "react";

const RegisterPageContainer = () => {
    const dispatch = useDispatch();
    const { resultMsg, error, form,loading } = useSelector(({member,loading }) => ({
        form :member.register,
        resultMsg: member.resultMsg,
        error: member.error,
        loading: loading['/member/register'],
        //user: findId.findId,
    }),
    );
    const onCheck = (e) => {
        const {value,name} = e.target;
        e.preventDefault();
        //dispatch(findId({ userName, email,phone}));
    };
    const onChange = (e) => {
        const {value,name} = e.target;
        dispatch(
            chengeField({
                form: 'register',
                key:name,
                value
            })
        );
    };
    const onRegister = (e) => {
        const { userId, userName, userPassword, userPasswordConfirm, email, phone, gender, age} = form;
        e.preventDefault();
        dispatch(registerData({ userId, userName, userPassword, userPasswordConfirm, email, phone, gender, age}));
    };
    useEffect(()=>{
        dispatch(initializeForm('register'));
    },[dispatch]);
  return (
    <>
      <RegisterForm
          type = "register"
          form={form}
          loading={loading}
          error={error}
          resultText={resultMsg}
          onClick={onRegister}
          onChange={onChange}
         // onCheck={onCheck}
      />
    </>
  );
};

export default withRouter(RegisterPageContainer);
