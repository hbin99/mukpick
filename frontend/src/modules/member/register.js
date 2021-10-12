import { createAction, handleActions } from 'redux-actions';
import * as memberAPI from '../../lib/api/member';
import createRequestSaga, {
  createRequestActionTypes,
} from '../../lib/createRequestSaga';
import { takeLatest } from 'redux-saga/effects';
import produce from "immer";

const CHANGE_FIELD ='register/CHANGE_FIELD';
const INITIALIZE_FORM ='register/INITIALIZE_FORM';

const [REGISTER, REGISTER_SUCCESS, REGISTER_FAILURE] =
    createRequestActionTypes('/member/register');

export const chengeField = createAction(
    CHANGE_FIELD,
    ({ form, key, value}) => ({
        form,
        key, // 컴포넌트
        value //실제 바꾸려는 값
    }),
);

export const initializeForm = createAction(INITIALIZE_FORM, form => form);
export const register = createAction(REGISTER,
    ({ userId,userName, userPassword, userPasswordConfirm,email,phone,gender,age }) =>({
        userId,
        userName,
        userPassword,
        userPasswordConfirm,
        email,
        phone,
        gender,
        age

}));

const initialState = {
    register: {
        userId: '',
        userName:'',
        userPassword: '',
        userPasswordConfirm: '',
        email: '',
        phone: '',
        gender: '',
        age: '',
    },
    resultMsg: null,
    error: null
};

//사가 생성
const registerDataSaga = createRequestSaga(
  REGISTER,
  memberAPI.register,
);
export function* registerSaga() {
  yield takeLatest(REGISTER, registerDataSaga);
}

const memberRegister = handleActions(
{
        [CHANGE_FIELD]: (state, { payload: { form, key, value} }) =>
            produce(state,draft => {
                draft[form][key] = value; //state.form.userName
          }),
        [INITIALIZE_FORM]: (state, { payload: form }) => ({
            ...state,
            [form] : initialState[form],
            error :null,
        }),
        [REGISTER_SUCCESS]: (state, { payload: resultMsg }) => ({
            ...state,
            error :null ,
            resultMsg
        }),
        [REGISTER_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error : error,
        }),
  },
  initialState,
);

export default memberRegister;
