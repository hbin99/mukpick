import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import loading from './loading';
import findUser, { memberSaga } from './member/findUser';
import user,{loginSaga} from './member/login';
import searchMntModule, {
  searchMntSaga,
  deleteSearchTextSaga,
  changeValidateSaga,
  transferSearchTextSaga,
} from './admin/searchMntModule';
import foodMntModule, { deleteFoodInfoSaga, getFoodInfoListSaga, updateFoodInfoSaga } from './admin/foodMntModule';

const rootReducer = combineReducers({
  loading,
  searchMnt: searchMntModule,
  foodMnt: foodMntModule,
  member: findUser,
  user
});

function* adminSaga(){
  yield all([
    searchMntSaga(),
    deleteSearchTextSaga(),
    changeValidateSaga(),
    transferSearchTextSaga(),
    getFoodInfoListSaga(),
    deleteFoodInfoSaga(),
    updateFoodInfoSaga(),
  ])
}

function* userSaga(){
  yield all([
    loginSaga(),
    memberSaga(),
  ])
}

export function* rootSaga() {
  yield all([
    adminSaga(),
    memberSaga()
  ]);
}



export default rootReducer;
