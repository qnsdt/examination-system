<template>
  <el-form ref="form" :model="user" :rules="rules" label-width="80px">
    <el-form-item label="旧密码" prop="oldPassword">
      <el-input v-model="user.oldPassword" placeholder="请输入旧密码" type="password" />
    </el-form-item>
    <el-form-item label="新密码" prop="newPassword">
      <el-input v-model="user.newPassword" placeholder="请输入新密码" type="password" />
    </el-form-item>
    <el-form-item label="确认密码" prop="confirmPassword">
      <el-input v-model="user.confirmPassword" placeholder="请确认密码" type="password" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" size="mini" @click="submit">保存</el-button>
      <el-button type="danger" size="mini" @click="close">关闭</el-button>
    </el-form-item>
    <p>注:密码需在8-20位之间,包括最少一位大小写字母、一位数字、及包括@#$%^*在内的最少一位特殊字符(系统不接受6位以外的特殊字符)</p>
  </el-form>
</template>

<script>
import { updateUserPwd } from "@/api/system/user";

export default {
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.user.newPassword !== value) {
        callback(new Error("两次输入的密码不一致"));
      } else {
        callback();
      }
    };
    const checkPasswordStrength = (rule, value, callback) => {
      // const regex = new RegExp('^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\W_!@#$%^&*`~()-+=]+$)(?![0-9\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\W_!@#$%^&*`~()-+=]{6,20}$');
      // const regex = new RegExp('^(?![a-zA-z]+$)(?!\d+$)(?![@#$%^*]+$)(?![a-zA-z\d]+$)(?![a-zA-z@#$%^*]+$)(?![\d@#$%^*]+$)[a-zA-Z\d@#$%^*]+$');
      // const regex = new RegExp('^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_@#$%^*]+$)(?![a-z0-9]+$)(?![a-z\W_@#$%^*]+$)(?![0-9\W_@#$%^*]+$)[a-zA-Z0-9\W_@#$%^*]{6,20}$');
      // if(!regex.test(value) && (value.length < 8 || value.length > 20)){
	    const lowerchar = "abcdefghijklmnopqrstuvwxyz";
	    const upperchar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    const numberbase = "0123456789";
		  const specialcharbase = "@#$%^*";

      if (value.length < 8 || value.length > 20) {  
          callback(new Error("密码需在8-20位之间"));
      }
      
      let islowerchar = 0;
      let isupperchar = 0;
      let isnumberbase = 0;
      let isspecialcharbase = 0;
      for (let i = 0; i < value.length; i ++) {
        // console.log( value.charAt(i) )
        if (lowerchar.indexOf(value.charAt(i)) >= 0 ) {
          islowerchar ++
        } else if ( upperchar.indexOf(value.charAt(i)) >= 0) {
          isupperchar ++ 
        } else if ( numberbase.indexOf(value.charAt(i)) >= 0) {
          isnumberbase ++ 
        } else if ( specialcharbase.indexOf(value.charAt(i)) >= 0) {
          isspecialcharbase ++ 
        }

      }

      if (islowerchar === 0) {
         callback(new Error("密码最少要包括一个小写字符!"));
      } else if (isupperchar === 0) {
         callback(new Error("密码最少要包括一个大写字符!"));
      }  else if (isnumberbase === 0) {
         callback(new Error("密码最少要包括一个数字!"));
      }  else if (isspecialcharbase === 0) {
         callback(new Error("密码最少要包括如下特殊字符@#$%^*，并只能是这六个特殊字符!"));
      } else {
         callback();

      }
 
      /* } else {
        
      } */
    };
    return {
      test: "1test",
      user: {
        oldPassword: undefined,
        newPassword: undefined,
        confirmPassword: undefined
      },
      // 表单校验
      rules: {
        oldPassword: [
          { required: true, message: "旧密码不能为空", trigger: "blur" }
        ],
        /* newPassword: [
          { required: true, message: "新密码不能为空", trigger: "blur" },
          { min: 6, max: 20, message: "长度在 6 到 20 个字符", trigger: "blur" }
        ], */
        newPassword: [
          { required: true, message: "新密码不能为空", trigger: "blur" },
          { required: true, validator: checkPasswordStrength, trigger: "blur" }
        ],        
        confirmPassword: [
          { required: true, message: "确认密码不能为空", trigger: "blur" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ]
      }
    };
  },
  methods: {
    submit() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateUserPwd(this.user.oldPassword, this.user.newPassword).then(
            response => {
              this.msgSuccess("修改成功");
            }
          );
        }
      });
    },
    close() {
      this.$store.dispatch("tagsView/delView", this.$route);
      this.$router.push({ path: "/index" });
    }
  }
};
</script>
