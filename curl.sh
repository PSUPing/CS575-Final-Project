echo Getting virus information for all viruses
echo

curl -X GET http://localhost:9000/viruses

echo  
echo Adding virus information to the DB
echo

curl -X POST http://localhost:9000/virus -H "Content-Type: application/json" -d '{"MD5": "80179d0592fa3857dfe928b4e05e3d3b", "SHA1": "86cca84db1e56fd071fb4068ef7e122a43491b48", "SHA256": "6101ec8a560d1a657ba223b11643998c98614a92a7953fc93d01c66530a25da9", "SSDeep": "24576:KtRCVLcUxFHT5DcoBh/rZ8bUbDP28XESrUKEUPWtSDBdISWUqqBhsTFzTt76:KmBHTFBh/Vgej2clPLDkPUfXsB3t76", "time_stamp": "2014-11-28 23:43:13 UTC", "av_scans": { "AVG": "Generic5.BPKA", "Avast": "Win32:Adware-gen [Adw]", "Kaspersky": "not-a-virus:AdWare.Win32.Agent.euxz", "Microsoft": "none", "Sophos": "Smart Apps Coupon Server", "AVware": "Trojan.Win32.Generic!BT", "AdAware": "Adware.Smartad.F",  "BaiduInternational": "none", "BitDefender": "Adware.Smartad.F", "ClamAV": "none", "ESETNOD32": "a variant of Win32/AdWare.SmartApps.I", "Emsisoft": "Adware.Smartad.F (B)", "McAfee": "Artemis!0FFCC49215A5", "McAfeeGWEdition": "none", "Symantec": "none", "VBA32": "AdWare.Agent", "nProtect": "Adware.Smartad.F" }, "file_information": { "size": 1462, "file_type": "Win32 EXE", "mime_type": "application/octet-stream", "code_size": 35328 }}'

echo  
echo Getting the virus information just added to the DB
echo

curl -X GET http://localhost:9000/virus/80179d0592fa3857dfe928b4e05e3d3b

echo