/*******************************************************************************
* Copyright (c) 2012 GigaSpaces Technologies Ltd. All rights reserved
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*       http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/
/***************
 * Cloud configuration file for the Amazon ec2 cloud. Uses the default jclouds-based cloud driver.
 * See org.cloudifysource.dsl.cloud.Cloud for more details.
 * @author uric
 *
 */

cloud {
    // Mandatory. The name of the cloud, as it will appear in the Cloudify UI.
    name = "ec2-ubuntu"

    /********
     * General configuration information about the cloud driver implementation.
     */
    configuration {
        // Optional. The cloud implementation class. Defaults to the build in jclouds-based provisioning driver.
        className "org.cloudifysource.esc.driver.provisioning.jclouds.DefaultProvisioningDriver"
        // Optional. The template name for the management machines. Defaults to the first template in the templates section below.
        managementMachineTemplate "MEDIUM_LINUX"
        // Optional. Indicates whether internal cluster communications should use the machine private IP. Defaults to true.
        connectToPrivateIp true
    }

    /*************
     * Provider specific information.
     */
    provider {
        // Mandatory. The name of the provider.
        // When using the default cloud driver, maps to the Compute Service Context provider name.
        provider "aws-ec2"

        // Optional. The HTTP/S URL where cloudify can be downloaded from by newly started machines. Defaults to downloading the
        // cloudify version matching that of the client from the cloudify CDN.
        // Change this if your compute nodes do not have access to an internet connection, or if you prefer to use a
        // different HTTP server instead.
        // cloudifyUrl "http://repository.cloudifysource.org/org/cloudifysource/2.2.0/gigaspaces-cloudify-2.2.0-m4-b2493-36.zip"

        // Mandatory. The prefix for new machines started for servies.
        machineNamePrefix "cloudify_agent_"
        // Optional. Defaults to true. Specifies whether cloudify should try to deploy services on the management machine.
        // Do not change this unless you know EXACTLY what you are doing.

        //
        managementOnlyFiles([])

        // Optional. Logging level for the intenal cloud provider logger. Defaults to INFO.
        sshLoggingLevel "WARNING"

        // Mandatory. Name of the new machine/s started as cloudify management machines. Names are case-insensitive.
        managementGroup "cloudify_manager"
        // Mandatory. Number of management machines to start on bootstrap-cloud. In production, should be 2. Can be 1 for dev.
        numberOfManagementMachines 1

        reservedMemoryCapacityPerMachineInMB 1024
    }

    /*************
     * Cloud authentication information
     */
    user {
        // Optional. Identity used to access cloud.
        // When used with the default driver, maps to the identity used to create the ComputeServiceContext.
        user "AWS_API_KEY"

        // Optional. Key used to access cloud.
        // When used with the default driver, maps to the credential used to create the ComputeServiceContext.
        apiKey "AWS_SECRET_KEY"

    }

    /***********
     * Cloud machine templates available with this cloud.
     */
    templates([            
            SMALL_LINUX: template { //ubuntu 12.04
                // Mandatory. Image ID.
                imageId "us-east-1/ami-a29943cb"
                // Mandatory. All files from this LOCAL directory will be copied to the remote machine directory.
                localDirectory "upload"
                // Mandatory. Files from the local directory will be copied to this directory on the remote machine.
                remoteDirectory "/home/ubuntu/gs-files"
                // Mandatory. Amount of RAM available to machine.
                machineMemoryMB 1600
                // Mandatory. Hardware ID.
                hardwareId "m1.small"
                // Optional. Location ID.
                locationId "us-east-1"
                // Optional. Name of key file to use for authenticating to the remot machine. Remove this line if key files
                // are not used.
                keyFile "KEYPAIR_FILE_NAME"

                // Additional template options.
                // When used with the default driver, the option names are considered
                // method names invoked on the TemplateOptions object with the value as the parameter.
                options([
                        "securityGroups": ["default"] as String[],
                        "keyPair": "KEYPAIR_NAME"
                ])

                // Optional. Overrides to default cloud driver behavior.
                // When used with the default driver, maps to the overrides properties passed to the ComputeServiceContext a
                overrides(["jclouds.ec2.ami-query": "",
                        "jclouds.ec2.cc-ami-query": ""])
                // enable sudo.
                privileged true
                //ssh user
                username "ubuntu"

            },
            MEDIUM_LINUX: template { //ubuntu 12.04
                // Mandatory. Image ID.
                imageId "us-east-1/ami-a29943cb"
                // Mandatory. All files from this LOCAL directory will be copied to the remote machine directory.
                localDirectory "upload"
                // Mandatory. Files from the local directory will be copied to this directory on the remote machine.
                remoteDirectory "/home/ubuntu/gs-files"
                // Mandatory. Amount of RAM available to machine.
                machineMemoryMB 1600
                // Mandatory. Hardware ID.
                hardwareId "m1.medium"
                // Optional. Location ID.
                locationId "us-east-1"
                // Optional. Name of key file to use for authenticating to the remot machine. Remove this line if key files
                // are not used.
                keyFile "KEYPAIR_FILE_NAME"

                // Additional template options.
                // When used with the default driver, the option names are considered
                // method names invoked on the TemplateOptions object with the value as the parameter.
                options([
                        "securityGroups": ["default"] as String[],
                        "keyPair": "KEYPAIRNAME"
                ])

                // Optional. Overrides to default cloud driver behavior.
                // When used with the default driver, maps to the overrides properties passed to the ComputeServiceContext a
                overrides(["jclouds.ec2.ami-query": "",
                        "jclouds.ec2.cc-ami-query": ""])
                // enable sudo.
                privileged true
                //ssh user
                username "ubuntu"

            },
            SMALL_UBUNTU: template { //ubuntu 12.04
                // Mandatory. Image ID.
                imageId "us-east-1/ami-a29943cb"
                // Mandatory. All files from this LOCAL directory will be copied to the remote machine directory.
                localDirectory "upload"
                // Mandatory. Files from the local directory will be copied to this directory on the remote machine.
                remoteDirectory "/home/ubuntu/gs-files"
                // Mandatory. Amount of RAM available to machine.
                machineMemoryMB 1600
                // Mandatory. Hardware ID.
                hardwareId "m1.small"
                // Optional. Location ID.
                locationId "us-east-1"
                // Optional. Name of key file to use for authenticating to the remot machine. Remove this line if key files
                // are not used.
                keyFile "KEYPAIR_FILE_NAME"

                // Additional template options.
                // When used with the default driver, the option names are considered
                // method names invoked on the TemplateOptions object with the value as the parameter.
                options([
                        "securityGroups": ["default"] as String[],
                        "keyPair": "KEYPAIR_NAME"
                ])

                // Optional. Overrides to default cloud driver behavior.
                // When used with the default driver, maps to the overrides properties passed to the ComputeServiceContext a
                overrides(["jclouds.ec2.ami-query": "",
                        "jclouds.ec2.cc-ami-query": ""])
                // enable sudo.
                privileged true
                //ssh user
                username "ubuntu"

            },
            MEDIUM_UBUNTU: template { //ubuntu 12.04
                // Mandatory. Image ID.
                imageId "us-east-1/ami-a29943cb"
                // Mandatory. All files from this LOCAL directory will be copied to the remote machine directory.
                localDirectory "upload"
                // Mandatory. Files from the local directory will be copied to this directory on the remote machine.
                remoteDirectory "/home/ubuntu/gs-files"
                // Mandatory. Amount of RAM available to machine.
                machineMemoryMB 1600
                // Mandatory. Hardware ID.
                hardwareId "m1.medium"
                // Optional. Location ID.
                locationId "us-east-1"
                // Optional. Name of key file to use for authenticating to the remot machine. Remove this line if key files
                // are not used.
                keyFile "KEYPAIR_FILE_NAME"

                // Additional template options.
                // When used with the default driver, the option names are considered
                // method names invoked on the TemplateOptions object with the value as the parameter.
                options([
                        "securityGroups": ["default"] as String[],
                        "keyPair": "KEYPAIRNAME"
                ])

                // Optional. Overrides to default cloud driver behavior.
                // When used with the default driver, maps to the overrides properties passed to the ComputeServiceContext a
                overrides(["jclouds.ec2.ami-query": "",
                        "jclouds.ec2.cc-ami-query": ""])
                // enable sudo.
                privileged true
                //ssh user
                username "ubuntu"

            }
    ])

    /*****************
     * Optional. Custom properties used to extend existing drivers or create new ones.
     */
    custom([:])
}

