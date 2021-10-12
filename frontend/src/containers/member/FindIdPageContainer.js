import { useDispatch, useSelector } from 'react-redux';
import {initializeForm, chengeField, findId} from '../../modules/member/findId';
import { withRouter } from 'react-router-dom';
import FindId from '../../components/member/FindId';
import {useEffect} from "react";

const FindIdPageContainer = () => {
  const dispatch = useDispatch();
  const { resultMsg, error, form,loading } = useSelector(({member,loading }) => ({
      form :member.findId,
      resultMsg: member.resultMsg,
      error: member.error,
      loading: loading['/member/findId'],
      //user: findId.findId,
    }),
  );

  const onClick = (e) => {
    const {userName, email, phone} = form;
    e.preventDefault();
      dispatch(findId({ userName, email,phone}));
  };
  const onChange = (e) => {
    const {value,name} = e.target;
    dispatch(
      chengeField({
          form: 'findId',
          key:name,
          value
      })
    );
  };
  useEffect(()=>{
      dispatch(initializeForm('findId'));
  },[dispatch]);
    return (
    <>
      <FindId
        type = "findId"
        form={form}
        loading={loading}
        error={error}
        resultText={resultMsg}
        onClick={onClick}
        onChange={onChange}
      />
    </>
  );
};

export default withRouter(FindIdPageContainer);
