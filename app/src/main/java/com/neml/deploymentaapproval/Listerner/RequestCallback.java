package com.neml.deploymentaapproval.Listerner;

    public interface RequestCallback {
        /**
         * Before thread start
         */
        public void onStart();

        /**
         * After thread complete
         *
         * @param object
         */
        public void onComplete(Object object);

        /**
         * Logs request by request
         *
         * @param current
         * @param total
         */
        public void onProgress(int current, int total);

        /**
         * If error occured during process
         *
         * @param errorMsg
         */

        public void onError(int transCode, String errorMsg);

    }


