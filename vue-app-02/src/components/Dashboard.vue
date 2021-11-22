<template>
  <v-card class="mx-auto" max-width="374">
    <form @submit.prevent="submit">
      <v-text-field
        v-model="name"
        :error-messages="nameErrors"
        :counter="19"
        label="Name"
        required
        @input="$v.name.$touch()"
        @blur="$v.name.$touch()"
      ></v-text-field>
      <v-text-field
        v-model="type"
        :error-messages="typeErrors"
        :counter="19"
        label="Type"
        required
        @input="$v.type.$touch()"
        @blur="$v.type.$touch()"
      ></v-text-field>
      <v-btn class="mr-4" @click="submit"> submit </v-btn>
      <v-btn @click="clear"> clear </v-btn>
    </form>
  </v-card>
</template>

<script>
import axios from 'axios';
import { validationMixin } from 'vuelidate'
import { required, maxLength } from 'vuelidate/lib/validators'
export default {
  mixins: [validationMixin],
  validations: {
      name: { required, maxLength: maxLength(19) },
      type: { required, maxLength: maxLength(19) } 
      },
  data() {
    return {
      name: "",
      type: "",
    };
  },
  computed: {
    nameErrors () {
        const errors = []
        if (!this.$v.name.$dirty) return errors
        !this.$v.name.maxLength && errors.push('Name must be at most 10 characters long')
        !this.$v.name.required && errors.push('Name is required.')
        return errors
      },
      typeErrors () {
        const errors = []
        if (!this.$v.type.$dirty) return errors
        !this.$v.type.maxLength && errors.push('Type must be at most 19 characters long')
        !this.$v.type.required && errors.push('Type is required.')
        return errors
      },
  },

  methods: {
    submit() {
      this.$v.$touch()
      var date = new Date();
      const formData = {
        dop:
          date.getUTCFullYear() +
          "-" +
          (date.getUTCMonth() + 1) +
          "-" +
          date.getUTCDate(),
        name: this.name,
        type: this.type,
      };
      console.log(formData);
      axios.post('http://localhost:8080/api/plant/', formData)
        .then(response => console.log(response));
    },
    clear() {
      (this.name = ""),
      (this.type = ""),
      this.$v.$reset();
    },
  },
};
</script>